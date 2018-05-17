(ns shopme-cd.steps
  (:require [lambdacd.steps.shell :as shell]
            [lambdacd-git.core :as git]
            [lambdacd.stepsupport.chaining :as chaining]
            [clj-slack.core :as slack]
            [environ.core :refer [env]]))

(def ^:private slack-url
  (env :slack-url))

(def ^:private slack-channel "#backend-ci")

(def ^:private repo-uri "https://github.com/blstream/ShopMe_Backend.git")
(def ^:private repo-branch "master")

(defn wait-for-repo
  [args ctx]
  (git/wait-for-git ctx repo-uri :ms-between-polls (* 5 60 1000)))

(defn clone
  [args ctx]
  (let [revision (:revision args)
        cwd      (:cwd args)
        ref      (or revision repo-branch)]
    (git/clone ctx repo-uri ref cwd)))

(def build-command
  "docker build -t backend-cd backend")

(defn docker-build
  [args ctx]
  (shell/bash ctx
              (:cwd args)
              build-command))

(defn slack-notification
  [args ctx]
  (let [link (slack/create-link "https://github.com/blstream/ShopMe_Backend/commits/master" "Inspect latest commits.")
        details (take-last 10 (clojure.string/split-lines (:out args)))
        message (str "Docker build for master branch failed!\nReproduce it by: " "`" build-command "`\n" link "\n```\n" details "\n```")
        result (slack/send-msg slack-url message :username "Shopme-CI" :icon_emoji ":ghost:" :channel slack-channel )]
    result))

(defn run-some-tests
  [args ctx]
  (chaining/last-step-status-wins
   (chaining/always-chaining args ctx
                             (docker-build chaining/injected-args chaining/injected-ctx)
                             (if (= :failure (:status chaining/injected-args))
                               (slack-notification chaining/injected-args chaining/injected-ctx)
                               {:status :success}))))

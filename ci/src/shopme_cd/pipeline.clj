(ns shopme-cd.pipeline
  (:use [lambdacd.steps.control-flow]
        [shopme-cd.steps])
  (:require
   [lambdacd.steps.manualtrigger :as manualtrigger])
  (:refer-clojure :exclude [alias]))

(def pipeline-def
  `(
    (alias "trigger"
           (either
            manualtrigger/wait-for-manual-trigger
            wait-for-repo))

    (alias "build and test"
           (with-workspace
             clone
             run-some-tests))))


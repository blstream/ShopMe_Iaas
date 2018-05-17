# shopme-cd

A continuous delivery pipeline for Shopme backend

## Build

    lein uberjar
    
## Run

    SLACK_URL="https://hooks.slack.com/services/[some secret]" \
    java --add-modules java.xml.bind -jar ci.jar

## Setup tunnel

    ssh -L 8000:localhost:8080 patronage -N
    
    http://localhost:8000/lambdaui/lambdaui/index.html

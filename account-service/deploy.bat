.\mvnw.cmd clean -DskipTests=true package
docker image rm dochero-account-service-latest
docker build -t phongvanngo/dochero-account-service:latest .
docker push phongvanngo/dochero-account-service:latest
#ENDPOINT=https://glacial-anchorage-84282.herokuapp.com/tcss360/messages
# local web runner deploy
ENDPOINT=http://localhost:8080/tcss360/messages
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/messages
# manual deploy
#ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/messages
curl -X PUT -H "Content-Type: application/json" -d @./message_update.json $ENDPOINT
echo

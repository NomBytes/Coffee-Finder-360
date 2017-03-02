ENDPOINT=https://safe-sea-61961.herokuapp.com/tcss360/reviews

# local web runner deploy
#ENDPOINT=http://localhost:8080/tcss360/users
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/users

curl -X DELETE -H "Content-Type: application/json" -d @./review_ex_delete.json $ENDPOINT
echo

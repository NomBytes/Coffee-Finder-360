ENDPOINT=https://safe-sea-61961.herokuapp.com/tcss360/users
#ENDPOINT=http://localhost:8080/tcss360/users
curl -X POST -H "Content-Type: application/json" -d @./mark.json $ENDPOINT
echo


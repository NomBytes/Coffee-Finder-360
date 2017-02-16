ENDPOINT=https://safe-sea-61961.herokuapp.com/tcss360/users
# local web runner deploy
#ENDPOINT=http://localhost:8080/tcss360/users
curl -X GET -H "Content-Type: application/html" $ENDPOINT
echo


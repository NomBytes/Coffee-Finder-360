ENDPOINT=https://safe-sea-61961.herokuapp.com/tcss360/reviews
# local web runner deploy
#ENDPOINT=http://localhost:8080/tcss360/reviews
curl -X GET -H "Content-Type: application/html" $ENDPOINT
echo

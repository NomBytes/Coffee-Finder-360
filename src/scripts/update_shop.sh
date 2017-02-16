ENDPOINT=https://safe-sea-61961.herokuapp.com/tcss360/shops

curl -X PUT -H "Content-Type: application/json" -d @./boogy_update.json $ENDPOINT
echo


ENDPOINT=https://safe-sea-61961.herokuapp.com/tcss360/reviews

curl -X PUT -H "Content-Type: application/json" -d @./review_ex_update.json $ENDPOINT
echo

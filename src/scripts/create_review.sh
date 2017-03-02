ENDPOINT=https://safe-sea-61961.herokuapp.com/tcss360/reviews
curl -X POST -H "Content-Type: application/json" -d @./review_ex.json $ENDPOINT
echo

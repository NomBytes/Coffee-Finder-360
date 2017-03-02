#ENDPOINT=https://safe-sea-61961.herokuapp.com/tcss360/shops
ENDPOINT=http://localhost:8080/tcss360/shops
curl -X POST -H "Content-Type: application/json" -d @./metro.json $ENDPOINT
echo


echo "." >> test.txt
git add test.txt
git commit -m "/pr create --title \"$1\" --base master --draft"
git push origin $1

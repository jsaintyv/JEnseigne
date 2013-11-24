#!/bin/bash

function genNombre { 
nombre=$1
literal=$2
convert -background lightblue -fill blue -font Verdana-Normal -pointsize 30 -size 165x70 -gravity center label:$nombre $literal.png
echo "listMots.add(create(\"$literal\", \"$literal.png\"));"
}

genNombre 1 un
genNombre 2 deux
genNombre 3 trois 
genNombre 4 quatre
genNombre 5 cinq
genNombre 6 six
genNombre 7 sept
genNombre 8 huit
genNombre 9 neuf
genNombre 10 dix
genNombre 11 onze
genNombre 12 douze
genNombre 13 treize
genNombre 14 quatorze
genNombre 15 quinze
genNombre 16 seize
genNombre 20 vingt
genNombre 30 trente
genNombre 40 quarante
genNombre 50 cinquante
genNombre 60 soixante
genNombre 100 cent
genNombre 1000 mille
genNombre 1000000 million

cp *.png ../src/org/jenseigne/dictionnaire/res

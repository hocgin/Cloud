#!/usr/bin/env bash
# need usa ngrok, default false
subDomain=cloud
port=8080
dir=/home/hocgin/Documents/NutzStore/Nutstore/toolBox/ngrok
echo $dir/bin/ngrok
$dir/bin/ngrok -subdomain $subDomain -proto=http -config=$dir/bin/ngrok.cfg $port
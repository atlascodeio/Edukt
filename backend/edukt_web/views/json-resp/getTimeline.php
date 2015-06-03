<?php

//Order by timeStamp function to use with usort
function ordertime($a, $b)
{
	if ($a['timeStamp'] == $b['timeStamp']) {
        return 0;
    }
    return ($a['timeStamp'] > $b['timeStamp']) ? -1 : 1;
}

usort($result, "ordertime");
$final = array('feed'=>$result);
$json = json_encode($final);
echo $json;

?>
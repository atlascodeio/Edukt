<?php

namespace app\controllers;

use Yii;
use app\models\Docs;
use app\models\Notas;




use app\models\DocsSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\db\Expression;

/**
 * DocsController implements the CRUD actions for Docs model.
 */
class JsonRespController extends Controller
{
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['post'],
                ],
            ],
        ];
    }

    /**
     * Return JsonArray with JsonObjects containing info ralated to Timeline (docs and notas)
     * @return mixed
     */
    public function actionGetTimeline()
    {
        
    	//find all the docs in array
    	$docs = Docs::find()->asArray()->limit(5)->orderBy('uid DESC')->all();
    	
    	//find all the notas in array
    	$notas = Notas::find()->asArray()->limit(5)->orderBy('uid DESC')->all();
    	
    	//prepare final array with results
    	$final = array();
    	//docs
    	foreach($docs as $key=>$doc){
    		$final[$key]['id'] = $doc['uid'];
    		$final[$key]['name'] = $doc['nombre'];
    		$final[$key]['image'] = 'http://api.androidhive.info/feed/img/cosmos.jpg';
    		$final[$key]['status'] = $doc['descripcion'];
    		$final[$key]['profilePic'] = 'http://api.androidhive.info/feed/img/nat.jpg';
    		$final[$key]['timeStamp'] = '1403375851930';
    		$final[$key]['url'] = null;
    	}
    	$len = count($final);
    	//notas
    	foreach($notas as $key=>$nota){
    		
    		$key_aux = $key + $len;
    		$final[$key_aux]['id'] = $nota['uid'];
    		$final[$key_aux]['name'] = $nota['nombre'];
    		$final[$key_aux]['image'] = 'http://api.androidhive.info/feed/img/cosmos.jpg';
    		$final[$key_aux]['status'] = $nota['descripcion'];
    		$final[$key_aux]['profilePic'] = 'http://api.androidhive.info/feed/img/nat.jpg';
    		$final[$key_aux]['timeStamp'] = '1403375851930';
    		$final[$key_aux]['url'] = null;
    	}
    	
    	
    	
    	
    	
    	/*
    	{
    		"id": 1,
    		"name": "National Geographic Channel",
            "image": "http://api.androidhive.info/feed/img/cosmos.jpg",
            "status": "\"Science is a beautiful and emotional human endeavor,\" says Brannon Braga, executive producer and director. \"And Cosmos is all about making science an experience.\"",
            "profilePic": "http://api.androidhive.info/feed/img/nat.jpg",
            "timeStamp": "1403375851930",
    	            		"url": null
    	}*/
    	
    	$final = array('feed'=>$final);
    	$json = json_encode($final);
    	echo $json;
    }

   
}

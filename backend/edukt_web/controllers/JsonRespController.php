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
<<<<<<< HEAD
    		$final[$key_aux]['id'] = $nota['uid'];
    		$final[$key_aux]['name'] = $nota['nombre'];
    		$final[$key_aux]['image'] = 'http://api.androidhive.info/feed/img/cosmos.jpg';
    		$final[$key_aux]['status'] = $nota['descripcion'];
    		$final[$key_aux]['profilePic'] = 'http://api.androidhive.info/feed/img/nat.jpg';
    		$final[$key_aux]['timeStamp'] = '1403375851930';
    		$final[$key_aux]['url'] = null;
=======
    		$result[$key_aux]['id'] = $nota['uid'];
    		$result[$key_aux]['name'] = $nota->users->name;
    		$result[$key_aux]['image'] = null;
    		$result[$key_aux]['status'] = $nota['descripcion'];
    		$result[$key_aux]['profilePic'] = $nota->users->profile_pic;
    		$result[$key_aux]['timeStamp'] = strtotime($nota['created_at']).'999';
    		//$final[$key_aux]['created_at'] = strtotime($doc['created_at']);
    		$result[$key_aux]['url'] = null;
>>>>>>> 1526136585946d7fd1d83ea3375b3b1bbc23fccc
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
<<<<<<< HEAD
=======
    
    /**
     * Return JsonArray with JsonObjects containing list of notas
     * @return mixed
     */
    public function actionGetNotifications()
    {
    
    	//find all the docs in array
    	$notas = Notas::find()->orderBy('uid DESC')->all();
    	 
    	//prepare final array with results
    	$result = array();
    	foreach($notas as $key=>$nota){
    		$result[$key]['id'] = $nota['uid'];
    		$result[$key]['name'] = $nota->users->name;
    		$result[$key]['image'] = null;
    		$result[$key]['status'] = $nota['descripcion'];
    		$result[$key]['profilePic'] = $nota->users->profile_pic;
    		$result[$key]['timeStamp'] = strtotime($nota['created_at']).'999';
    		$result[$key]['url'] = null;
    	}
    	 
    	$final = array('feed'=>$result);
    	$json = json_encode($final);
    	echo $json;
    	 
    }
    
    
    /**
     * Return JsonArray with JsonObjects containing list of docs
     * @return mixed
     */
    public function actionGetDocuments()
    {
    
    	//find all the docs in array
    	$docs = Docs::find()->orderBy('uid DESC')->with('users')->all();
    
    	//prepare final array with results
    	$result = array();
    	foreach($docs as $key=>$doc){
    		$result[$key]['id'] = $doc->uid;
    		$result[$key]['name'] = $doc->users->name;
    		$result[$key]['image'] = null;
    		$result[$key]['status'] = $doc->descripcion;
    		$result[$key]['profilePic'] = $doc->users->profile_pic;
    		$result[$key]['timeStamp'] = strtotime($doc->created_at).'999';
    		$result[$key]['url'] = $doc->url_doc;
    	}
    
    	$final = array('feed'=>$result);
    	$json = json_encode($final);
    	echo $json;
    
    }
    
    /**
     * Return JsonArray with JsonObjects containing data of user loged account
     * @return mixed
     */
    public function actionGetMyAccount()
    {
    
    	$id = 10;
    	//find the user loged data object
    	$user = Users::findOne(['uid'=>$id]);
    
    	//prepare final array with results
    	$result = array();
    	$result['id'] = $user->uid;
    	$result['name'] = $user->name;
    	$result['image'] = null;
    	$result['status'] = 'Universidad: '.$user->universidad->nombre;
    	$result['profilePic'] = $user->profile_pic;
    	$result['timeStamp'] = null;
    	$result['url'] = null;
    	$result['email'] = 'Email: '.$user->email;
    	
    	$json = json_encode($result);
    	echo $json;
    
    }
    
    
    
    
    
>>>>>>> 1526136585946d7fd1d83ea3375b3b1bbc23fccc

   
}

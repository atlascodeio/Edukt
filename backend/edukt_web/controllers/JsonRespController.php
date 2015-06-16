<?php

namespace app\controllers;

use Yii;
use app\models\Docs;
use app\models\Notas;
use app\models\Users;
use app\models\Universidad;


use app\models\DocsSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\db\Expression;





/**
 * JsonRespController implement all celphone app web interaction,  must be converted to RESfull API
 */
class JsonRespController extends Controller
{
	
	//To avoid the 400 error returned by yii at the request  body with json objects params
	public $enableCsrfValidation = false;
	
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
    	$docs = Docs::find()->limit(5)->orderBy('uid DESC')->with('users')->all();
    	
    	//find all the notas in array
    	$notas = Notas::find()->limit(5)->orderBy('uid DESC')->all();
    	
    	//prepare final array with results
    	$result = array();
    	//docs
    	foreach($docs as $key=>$doc){
    		$result[$key]['id'] = $doc->uid;
    		$result[$key]['name'] = $doc->users->name;
    		$result[$key]['image'] = null;
    		$result[$key]['status'] = $doc->descripcion;
    		$result[$key]['profilePic'] = $doc->users->profile_pic;
    		$result[$key]['timeStamp'] = strtotime($doc->created_at).'999';
    		$result[$key]['url'] = $doc->url_doc;
    		$result[$key]['nombre'] = $doc->nombre;
    	}
    	$len = count($result);
    	//notas
    	foreach($notas as $key=>$nota){
    		$key_aux = $key + $len;
    		$result[$key_aux]['id'] = $nota['uid'];
    		$result[$key_aux]['name'] = $nota->users->name;
    		$result[$key_aux]['image'] = null;
    		$result[$key_aux]['status'] = $nota['descripcion'];
    		$result[$key_aux]['profilePic'] = $nota->users->profile_pic;
    		$result[$key_aux]['timeStamp'] = strtotime($nota['created_at']).'999';
    		//$final[$key_aux]['created_at'] = strtotime($doc['created_at']);
    		$result[$key_aux]['url'] = null;
    	}
    	
    	//Render partial becasue is a Json respond
    	return $this->renderPartial('getTimeline', array('result'=>$result));
    }
    
    
    /**
     * Return JsonArray with JsonObjects containing list of teachers
     * @return mixed
     */
    public function actionGetTeachers()
    {
    
    	//find all the docs in array
    	$teachers = Users::find()->where(array('tipo_user'=>'Profesor'))->all();
    	
    	//prepare final array with results
    	$result = array();
    	foreach($teachers as $key=>$teacher){
    		$result[$key]['id'] = $teacher->uid;
    		$result[$key]['name'] = $teacher->name;
    		$result[$key]['image'] = null;
    		$result[$key]['status'] = 'Universidad: '.$teacher->universidad->nombre;
    		$result[$key]['profilePic'] = $teacher->profile_pic;
    		$result[$key]['timeStamp'] = null;
    		$result[$key]['url'] = null;
    		$result[$key]['email'] = 'Email: '.$teacher->email;
    	}
    	
    	$final = array('feed'=>$result);
    	$json = json_encode($final);
    	echo $json;
    	
    }
    
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
    		$result[$key]['nombre'] = $doc->nombre;
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
    
    	$body = file_get_contents('php://input');
    	$postvars = json_decode($body, true);
    	$unique_id = $postvars["id"];
    	
    	//$id = 10;
    	//find the user loged data object
    	$user = Users::findOne(['unique_id'=>$unique_id]);
    
    	//prepare final array with results
    	$result = array();
    	$result['id'] = $user->uid;
    	$result['name'] = $user->name;
    	$result['image'] = null;
    	$result['status'] = $user->universidad->nombre;
    	$result['profilePic'] = $user->profile_pic;
    	$result['timeStamp'] = null;
    	$result['url'] = null;
    	$result['email'] = $user->email;
    	$result['cedula'] = $user->cedula;
    	
    	$json = json_encode($result);
    	echo $json;
    }
    
    
    /**
     * Return JsonArray with JsonObjects containing list of universidades
     * @return mixed
     */
    public function actionGetUniversidades()
    {
    
    	//find all the docs in array
    	$universidades = Universidad::find()->orderBy('nombre ASC')->all();
    	 
    	//prepare final array with results
    	$result = array();
    	foreach($universidades as $key=>$universidad){
    		$result[$key]['id'] = $universidad->uid;
    		$result[$key]['nombre'] = $universidad->nombre;
    	}
    	 
    	$final = array('feed'=>$result);
    	$json = json_encode($final);
    	echo $json;
    	 
    }
    
    
    /**
     * Return string with 1 success and 0 error
     * @return mixed
     */
    public function actionAddNotifications()
    {
    
    	//dont need read input cuz stringRequest
    	//$body = file_get_contents('php://input');
    	//$postvars = json_decode($body, true);
    	$unique_id = $_POST["id"];
    	
    	//find the user loged data object
    	$user = Users::findOne(['unique_id'=>$unique_id]);
    	$notas = new Notas();
    	$notas->nombre = $_POST["nombre"];
    	$notas->descripcion = $_POST["descripcion"];
    	$notas->users_id = $user->uid;
    	$notas->created_at = new Expression("NOW()");
    	return $notas->save(false);
    }
    
    
    
    
    

   
}

<?php

namespace app\controllers;

use Yii;
use app\models\Docs;
use app\models\Notas;
use app\models\Users;



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
    		$result[$key_aux]['timeStamp'] = strtotime($doc['created_at']).'999';
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
    
    
    
    
    

   
}

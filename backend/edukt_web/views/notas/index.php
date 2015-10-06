<?php

use yii\helpers\Html;
use yii\grid\GridView;
use yii\bootstrap\Modal;
use yii\helpers\Url;
use yii\widgets\Pjax;
use yii\grid\DataColumn;

/* @var $this yii\web\View */
/* @var $searchModel app\models\NotasSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Mensaje';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="notas-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= Html::button('Crear', ['value'=>Url::to('create'),'class' => 'btn btn-success','id'=>'modalButton']) ?>

    <?php
      Modal::Begin([
              'header'=>'<h4>Crear Mensaje</h4>',
              'id'=>'modal',
              'size'=>'modal-lg',
        ]);
      echo "<div id='modalContent'></div>";
      Modal::end();
      ?>


    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        //'filterModel' => $searchModel,

        'columns' => [

            ['class' => 'yii\grid\SerialColumn'],
            //'uid',
            //'nombre',
            'descripcion',
            'created_at',
            //'updated_at',
            ['attribute'=>'users_id','value'=>'users.name'],
            ['class' => 'yii\grid\ActionColumn','header' => 'Acciones'],

        ],
    ]); ?>

</div>

<div class="form-group">

  <?= Html::button('Crear', ['value'=>Url::to('index.php?r=notas/create'),'class' => 'btn btn-success','id'=>'modalButton']) ?>

</div>

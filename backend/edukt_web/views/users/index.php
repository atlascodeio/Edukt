<?php

use yii\helpers\Html;
use yii\grid\GridView;
use app\models\Universidad;

/* @var $this yii\web\View */
/* @var $searchModel app\models\UsersSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Administrar usuarios';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="users-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Crear', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],



            //'uid',
            //'unique_id',
            'cedula',
            'name',
            'tipo_user',
            //'universidad.nombre',
            ['attribute'=>'universidad_id','value'=>'universidad.nombre',],
            'email:email',
            //'encrypted_password',
            // 'salt',
            //'created_at',
            //'updated_at',
            //'profile_pic',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>

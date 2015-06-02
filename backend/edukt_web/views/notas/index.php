<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $searchModel app\models\NotasSearch */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Notas';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="notas-index">

    <h1><?= Html::encode($this->title) ?></h1>
    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <p>
        <?= Html::a('Create Notas', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'uid',
            'nombre',
            'descripcion',
            'created_at',
            'updated_at',
            // 'users_id',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>

</div>

<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Universidad */

$this->title = 'Update Universidad: ' . ' ' . $model->uid;
$this->params['breadcrumbs'][] = ['label' => 'Universidads', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->uid, 'url' => ['view', 'id' => $model->uid]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="universidad-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>

<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model app\models\Notas */

$this->title = 'Update Notas: ' . ' ' . $model->uid;
$this->params['breadcrumbs'][] = ['label' => 'Notas', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->uid, 'url' => ['view', 'id' => $model->uid]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="notas-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>

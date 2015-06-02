<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Universidad */

$this->title = 'Crear universidad';
$this->params['breadcrumbs'][] = ['label' => 'Universidades', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="universidad-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>

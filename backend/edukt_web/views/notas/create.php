<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\Notas */

$this->title = 'Crear mensaje';
$this->params['breadcrumbs'][] = ['label' => 'Notas', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="notas-create">

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>

<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use app\models\Users;

/* @var $this yii\web\View */
/* @var $model app\models\NotasSearch */
/* @var $form yii\widgets\ActiveForm */
?>
<div class="">
  <div class="notas-search">

      <?php $form = ActiveForm::begin([
          'action' => ['index'],
          'method' => 'get',
      ]); ?>


      <?= $form->field($model, 'descripcion') ?>

      <?= $form->field($model, 'users_id')->dropDownList(ArrayHelper::map(users::find()->where( array('tipo_user'=>'Profesor'))->all(),'uid','name'),
      ['prompt'=>'Seleccione'])
      ?>

      <div class="form-group">
          <?= Html::submitButton('Buscar', ['class' => 'btn btn-primary']) ?>
          <?= Html::resetButton('Limpiar', ['class' => 'btn btn-default']) ?>
      </div>


      <?php ActiveForm::end(); ?>

      
  </div>
</div>

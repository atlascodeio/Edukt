<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use dosamigos\datepicker\DatePicker;

/* @var $this yii\web\View */
/* @var $model app\models\Documentos */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="documentos-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'nombre')->textInput(['maxlength' => 100]) ?>

    <?= $form->field($model, 'tipo')->textInput(['maxlength' => 50]) ?>

    <?= $form->field($model, 'url_doc')->textInput(['maxlength' => 250]) ?>

    <?= $form->field($model, 'created_at')->widget(DatePicker::className(), [
      'clientOptions' => [
        'autoclose' => true,
        'format' => 'yyyy-mm-dd'
        ]
        ]);?>

    <?= $form->field($model, 'updated_at')->widget(DatePicker::className(), [
      'clientOptions' => [
        'autoclose' => true,
        'format' => 'yyyy-mm-dd'
        ]
        ]);?>

    <?= $form->field($model, 'descripcion')->textInput(['maxlength' => 250]) ?>

    <?= $form->field($model, 'img_url')->textInput(['maxlength' => 250]) ?>

    <?= $form->field($model, 'users_id')->textInput() ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>

<?php

use yii\helpers\Html;
use yii\helpers\ArrayHelper;
use yii\widgets\ActiveForm;
use app\models\Universidad;

/* @var $this yii\web\View */
/* @var $model app\models\Users */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="users-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'cedula')->textInput(['maxlength' => 8]) ?>

    <?= $form->field($model, 'name')->textInput(['maxlength' => 50]) ?>

    <?= $form->field($model, 'email')->textInput(['maxlength' => 100]) ?>

    <?= $form->field($model, 'encrypted_password')->textInput(['maxlength' => 80]) ?>

    <?= $form->field($model, 'universidad_id')->dropDownList(
          ArrayHelper::map(universidad::find()->all(),'uid','nombre'),
          ['prompt'=>'Seleccione su universidad']
    ) ?>

    <?= $form->field($model, 'tipo_user')->dropDownList([ 'Alumno' => 'Alumno', 'Profesor' => 'Profesor', ], ['prompt' => 'Seleccione']) ?>

    <?= $form->field($model, 'profile_pic')->textInput(['maxlength' => 250]) ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Crear usuario' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>

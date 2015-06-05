<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use app\models\Users;

/* @var $this yii\web\View */
/* @var $model app\models\Notas */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="notas-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'descripcion')->textarea(['maxlength' => 250]) ?>

    <?= $form->field($model, 'users_id')->dropDownList(ArrayHelper::map(users::find()->where( array('tipo_user'=>'Profesor'))->all(),'uid','email'),
      ['prompt'=>'Seleccione'])
    ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Crear' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>

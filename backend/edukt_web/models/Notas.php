<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "notas".
 *
 * @property integer $uid
 * @property string $nombre
 * @property string $descripcion
 * @property string $created_at
 * @property string $updated_at
 * @property integer $users_id
 *
 * @property Users $users
 */
class Notas extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'notas';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['nombre', 'descripcion', 'created_at', 'updated_at', 'users_id'], 'required'],
            [['created_at', 'updated_at'], 'safe'],
            [['users_id'], 'integer'],
            [['nombre'], 'string', 'max' => 100],
            [['descripcion'], 'string', 'max' => 250]
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'uid' => 'Uid',
            'nombre' => 'Nombre',
            'descripcion' => 'Descripción',
            'created_at' => 'Creado el',
            'updated_at' => 'Actualizado el',
            'users_id' => 'Creado por',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUsers()
    {
        return $this->hasOne(Users::className(), ['uid' => 'users_id']);
    }
}

<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "universidad".
 *
 * @property integer $uid
 * @property string $nombre
 * @property string $created_at
 * @property string $updated_at
 *
 * @property Users[] $users
 */
class Universidad extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'universidad';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['nombre', 'created_at', 'updated_at'], 'required'],
            [['created_at', 'updated_at'], 'safe'],
            [['nombre'], 'string', 'max' => 50]
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
            'created_at' => 'Creada el',
            'updated_at' => 'Actualizada el',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUsers()
    {
        return $this->hasMany(Users::className(), ['universidad_id' => 'uid']);
    }
}

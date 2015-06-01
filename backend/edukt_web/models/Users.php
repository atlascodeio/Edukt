<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "users".
 *
 * @property integer $uid
 * @property string $unique_id
 * @property string $name
 * @property string $email
 * @property string $encrypted_password
 * @property string $salt
 * @property string $created_at
 * @property string $updated_at
 * @property integer $universidad_id
 * @property integer $tipo_user
 * @property integer $cedula
 * @property string $profile_pic
 */
class Users extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'users';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['unique_id', 'name', 'email', 'encrypted_password', 'salt', 'universidad_id', 'tipo_user', 'cedula', 'profile_pic'], 'required'],
            [['created_at', 'updated_at'], 'safe'],
            [['universidad_id', 'tipo_user', 'cedula'], 'integer'],
            [['unique_id'], 'string', 'max' => 23],
            [['name'], 'string', 'max' => 50],
            [['email'], 'string', 'max' => 100],
            [['encrypted_password'], 'string', 'max' => 80],
            [['salt'], 'string', 'max' => 10],
            [['profile_pic'], 'string', 'max' => 250],
            [['unique_id'], 'unique'],
            [['email'], 'unique']
        ];
    }


    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'uid' => 'Uid',
            'unique_id' => 'Unique ID',
            'name' => 'Nombre',
            'email' => 'Email',
            'encrypted_password' => 'Encrypted Password',
            'salt' => 'Salt',
            'created_at' => 'Fecha de creacion',
            'updated_at' => 'Actualizado el',
            'universidad_id' => 'Universidad',
            'tipo_user' => 'Tipo usuario',
            'cedula' => 'Cedula',
            'profile_pic' => 'Foto de perfil',
        ];
    }
}

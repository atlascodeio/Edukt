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
 * @property string $tipo_user
 * @property integer $cedula
 * @property string $profile_pic
 *
 * @property Docs[] $docs
 * @property Notas[] $notas
 * @property Universidad $universidad
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
            [['universidad_id', 'cedula'], 'integer'],
            [['tipo_user'], 'string'],
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
            'created_at' => 'Creado',
            'updated_at' => 'Actualizado',
            'universidad_id' => 'Universidad',
            'tipo_user' => 'Tipo',
            'cedula' => 'Cedula',
            'profile_pic' => 'Foto de perfil',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getDocs()
    {
        return $this->hasMany(Docs::className(), ['users_id' => 'uid']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getNotas()
    {
        return $this->hasMany(Notas::className(), ['users_id' => 'uid']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getUniversidad()
    {
        return $this->hasOne(Universidad::className(), ['uid' => 'universidad_id']);
    }
}

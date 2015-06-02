<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "docs".
 *
 * @property integer $uid
 * @property string $nombre
 * @property string $tipo
 * @property string $url_doc
 * @property string $created_at
 * @property string $updated_at
 * @property string $descripcion
 * @property string $img_url
 * @property integer $users_id
 *
 * @property Users $users
 */
class Docs extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'docs';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['nombre', 'tipo', 'url_doc', 'created_at', 'updated_at', 'descripcion', 'img_url', 'users_id'], 'required'],
            [['created_at', 'updated_at'], 'safe'],
            [['users_id'], 'integer'],
            [['nombre'], 'string', 'max' => 100],
            [['tipo'], 'string', 'max' => 50],
            [['url_doc', 'descripcion', 'img_url'], 'string', 'max' => 250]
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
            'tipo' => 'Tipo',
            'url_doc' => 'Url documento',
            'created_at' => 'Creado el',
            'updated_at' => 'Actualizado el',
            'descripcion' => 'Descripción',
            'img_url' => 'Imagen',
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

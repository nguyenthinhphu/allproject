<?php

class BirthdayMay extends AppModel
{
    public $name = 'BirthdayMay';
    public $table = 'birthday_mays';
    public $alias = 'BirthdayMay';

    /**
     * Insert user month birthday = 05 into database
     * return true: success
     *        false: error
     */
    public function insertUserMay($users)
    {
        try {
        foreach ($users as $key => $value) {
            $sql = "INSERT INTO `birthday_mays` (`user_id`, `user_name`, `email`, `birthday`)
             VALUES ('".$value['User']['id']."' , '".$value['User']['user_name']."', '".$value['User']['email']."', '".
                date('Y-m-d', strtotime(str_replace('/', '-', $value['User']['birthday'])))."')";
            $this->query($sql);
        }
        } catch(Exception $e) {

            return false;
        }
        return true;
    }
}
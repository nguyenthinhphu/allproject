<?php
/**
 * Created by PhpStorm.
 * User: nguyenthinhphu
 * Date: 2/11/2020
 * Time: 2:16 PM
 */
class HelloShell extends AppShell {

    public $uses = ['User', 'BirthdayMay'];

    /**
     * Function xử lý batch chính
     */
    public function main() {

        $user =  $this->User->findAllUserInMay(5);

        $check = $this->BirthdayMay->insertUserMay($user);

        if ($check) {
            echo "Insert data Successfull!";
            exit();
        } else {
            echo "Insert Data Error.. Check Again!";
            exit();
        }

    }
}
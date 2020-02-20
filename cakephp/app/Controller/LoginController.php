<?php
/**
 * Created by PhpStorm.
 * User: nguyenthinhphu
 * Date: 11/26/2019
 * Time: 4:20 PM
 */
 class LoginController extends AppController {

     public $name = "Login";
     public $layout = 'logged';
     public $uses =  ['User', 'UserType'];
     public $components = ['Session', 'Flash', 'Paginator'];
     public $helpers = ['Paginator', 'Html', 'Form'];

     /**
      * Vào màn hình login user
      */
     public function view()
     {
         $erros = [];
        if ($this->request->is('post')) {

            $email = $this->request->data['email'];  // get user name
            $password = $this->request->data['password']; // get password

            $this->User->set($this->data);
            if ($this->data) {
                if (!$this->User->validateUser()) {
                    $erros = $this->User->validationErrors; // Get message lỗi từ biến validate
                } else {
                    $this->loadModel('User');
                    if (!$this->User->checkExistUser($email, $password)) { // Check tồn tại email và password trong DB
                        $erros = ["validEmailPassword" => ["Email hoặc Mật khẩu không đúng!"]];
                    }
                }
                $users = ['email' => $email, 'password' => $password];
                if (!empty($erros)) {
                    $this->set("dataError", $erros);

                    $this->set("dataUser", $users);

                    $this->render('/Users/login');
                } else {

                    // Lấy type của user và set user vào session
                    $userType = $this->User->getTypeUser($users['email'], $users['password']);
                    $type = $userType[0]['UserType']['id'];
                    $users['type'] = $type;
                    $users['id'] = $userType[0]['User']['id'];  // lấy id của user

                    // set user vào session và sang trang index
                    $this->Session->write('userSession', $users);
                    $this->redirect('/index');  // chuyển sang trang list
                }
        }
     }

        $this->Session->destroy();  // hủy toán bộ session nếu về trang login
        $this->render('/Users/login');
     }

     /**
      * Xử lý logic cho màn hình list user
      */
     public function index()
     {
         $listDataUsers = [];
         $mailSearch = '';
         $userType = 0;

         // Get từ session để lấy user type trong trường hợp vào màn hình list đầu tiên
         $sessionData = [];
         $sessionData['userType'] = $this->Session->read('userSession');

         // Kiểm tra nếu chưa login, trở về màn hình login
         if (!isset($sessionData['userType']))
         {
             $this->redirect('/login');
             return;
         }

         if (isset($sessionData['userType']) && !empty($sessionData['userType'])) {
             $user = $sessionData['userType'];
             $userType = $user['type'];
         }

         // kiểm tra khi click button Search

         if ($this->request->is('post')) {
             $mailSearch = $this->request->data['mailSearch'];
         }

         // get all data user
         $listDataUsers = $this->User->getListUsers($this->Paginator, $mailSearch, $userType);
         $totalUser = $listDataUsers['count'];

         // set data xuống view
         $this->set("dataUsers", $listDataUsers);
         $this->set("totalUser", $totalUser);
         $this->set("mailSearch", $mailSearch);
         $this->set('userType', $userType);

         // Chuyển sang view
         $this->render('/Users/list');
     }

     /**
      * Xử lý logic cho màn hình add
      */

     public function add()
     {
         $erros = [];
         $dataUserAdd = [];

         // Get từ session để lấy user type trong trường hợp vào màn hình list đầu tiên
         $sessionData = [];
         $sessionData['userType'] = $this->Session->read('userSession');

         // Nếu chưa login thì quay về trang login
         if (!isset($sessionData['userType'])) {
             $this->redirect('/login');
             return;
         }
         // Kiểm tra vào màn hình add - chỉ có admin mới được vào
         if (isset($sessionData['userType']) && !empty($sessionData['userType'])) {

             $user = $sessionData['userType'];
             $userType = $user['type'];

             // Kiểm tra nếu là type User thì về trang index
             if ($userType == 2) {
                 $this->redirect('/index');
                 return;
             }
         }

         // Khi có sự kiện click button Add
         if ($this->request->is('post')) {

             // set biến validate data nhập
             $this->User->set($this->data);

             if ($this->data) {
                 if (!$this->User->validateAddUser($this->data)) {
                     $erros = $this->User->validationErrors; // Lấy mảng lỗi từ biến validate từ model
                 }
             }

             // set biến user add để thực hiện save data và hiển thị giữ giá trị nhập
             $dataUserAdd = $this->data;

             if (!empty($erros)) {

                 $this->set("dataError", $erros);

                 $this->set("dataUser", $dataUserAdd);

                 $this->render('/Users/add');
             } else {

                 // Thực hiện insert dữ liệu vào DB
                 if ($this->User->insertUser($dataUserAdd)) {
                     $this->redirect('/index');
                 } else {
                     echo " Lỗi insert rồi...!!"; exit();
                 }
             }
         }
         $this->render('/Users/add');
     }

     public function edit($id)
     {

         // get data từ session để xử lý logic
         $sessionData = [];
         $sessionData['user'] = $this->Session->read('userSession'); // get data user login từ session

         // Kiểm tra nếu chưa login, trở về màn hình login
         if (!isset($sessionData['user']))
         {
             $this->redirect('/login');
             return;
         }

         // Vào màn hình edit lần đầu
         $dataUserEdit = [];

         $userData = $this->User->findUserById($id); // get user data theo id được edit

         // Trường hợp lỗi nhập id, hoặc không có data user
         if (!isset($userData) || empty($userData)) {
             $this->redirect('/index');
             return;
         }

         // Format lại định dạng user data để điền vào input
         foreach ($userData as $key => $value) {

             $dataUserEdit['id'] = $value['User']['id'];
             $dataUserEdit['username'] = $value['User']['user_name'];
             $dataUserEdit['type'] = $value['UserType']['type_name'];
             $dataUserEdit['email'] = $value['User']['email'];
             $dataUserEdit['address'] = $value['User']['address'];
             $dataUserEdit['birthday'] = $value['User']['birthday'];

         }

         // Tạo thuộc tính readonly để điền vào các input trường hợp user type là User
         if ($sessionData['user']['type'] == '2' && $sessionData['user']['id'] != $dataUserEdit['id'])
         {
             $dataUserEdit['readonly'] = 'readonly';
         }

         // Khi có sự kiện click button Edit
         if ($this->request->is('post')) {

             // set biến validate data nhập
             $this->User->set($this->data);

             if ($this->data) {
                 if (!$this->User->validateEditUser($this->data)) {
                     $erros = $this->User->validationErrors; // Lấy mảng lỗi từ biến validate từ model
                 }
             }

             // set biến user add để thực hiện save data và hiển thị giữ giá trị nhập
             $dataUserEdit = $this->data;
             $dataUserEdit['id'] = $id;

             // Trường hợp lỗi validate input, hiển thị lỗi
             if (!empty($erros)) {

                 $this->set("dataError", $erros);

                 $this->set("dataUser", $dataUserEdit);

                 $this->render('/Users/edit');
                 return;

             } else {

                 // Thực hiện insert dữ liệu vào DB
                 if ($this->User->updateUser($dataUserEdit)) {
                     $this->redirect('/index');
                 } else {
                     echo " Lỗi update rồi...!!"; exit();
                 }
             }
         }

         $this->set("dataUser", $dataUserEdit);

         $this->render('/Users/edit');
     }

     public function delete($id)
     {

         // Get từ session lấy user type kiểm tra quyền delete
         $sessionData = [];
         $sessionData['userType'] = $this->Session->read('userSession');

         // Nếu chưa login thì quay về trang login
         if (!isset($sessionData['userType'])) {
             $this->redirect('/login');
             return;
         }

         // Nếu tồn tại user type
         if (isset($sessionData['userType']) && !empty($sessionData['userType'])) {

             $user = $sessionData['userType'];
             $userType = $user['type'];

             // Kiểm tra nếu là type User thì về trang index
             if ($userType == 2) {
                 $this->redirect('/index');
                 return;
             }
         }

         // Vào màn hình edit lần đầu
         $dataUserEdit = [];

         $userData = $this->User->findUserById($id); // get user data theo id được edit

         // Trường hợp lỗi nhập it, hoặc không có data user
         if (!isset($userData) || empty($userData)) {
             $this->redirect('/index');
             return;
         }

         // Format lại định dạng user data để điền vào input
         foreach ($userData as $key => $value) {

             $dataUserEdit['id'] = $value['User']['id'];
             $dataUserEdit['username'] = $value['User']['user_name'];
             $dataUserEdit['type'] = $value['UserType']['type_name'];
             $dataUserEdit['email'] = $value['User']['email'];
             $dataUserEdit['address'] = $value['User']['address'];
             $dataUserEdit['birthday'] = $value['User']['birthday'];

         }

         // Khi có sự kiện click button Delete
         if ($this->request->is('post')) {

             $dataUserEdit['id'] = $id;
                 // Thực hiện insert dữ liệu vào DB
                 if ($this->User->deleteUser($dataUserEdit)) {
                     $this->redirect('/index');
                 } else {
                     echo " Lỗi delete rồi...!!"; exit();
                 }
         }

         // set data xuống view
         $this->set("dataUser", $dataUserEdit);

         $this->render('/Users/delete');
     }

 }
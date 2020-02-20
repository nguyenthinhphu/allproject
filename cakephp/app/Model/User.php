<?php
class User extends AppModel {
    public $name = 'User';
    public $table = 'users';
    public $alias = 'User';
    public $validate = [];

    // Relation với table user_types, quan hệ N-1
    public $belongsTo =  [
        'UserType' => [
            'className' => 'UserType',
            'foreignKey' => 'user_types_id',
            'conditions' => '',
            'fields' => '',
            'order' => ''
        ]
    ];

    /**
     * Validate user using form
     * @return bool
     */
    public function validateUser()
    {
        $this->validate = [
            "email" => [
                "dk1" => [
                "rule" => "notBlank",
                "message" => "Hãy nhập Email!",
                ],
                "dk2" => [
                    "rule" => "/^[a-z A-Z]{1}[a-z A-Z 0-9_]+\@[a-z A-Z 0-9]{2,}\.[a-z A-Z]{2,}$/i",
                    "message" => "Hãy nhập đúng định dạng Email!",
                ]
            ],
            "password" => [
                "rule" => "notBlank",
                "message" => "Hãy nhập password!",
            ],
        ];

        if($this->validates($this->validate))
            return true;
        else
            return false;
    }

    /**
     * Validate khi add user
     */
    public function validateAddUser($data)
    {

        $this->validate = [
            "username" => [
                "dk1" => [
                "rule" => "notBlank",
                "message" => "User Name không để trống!"
                    ],
                "dk2" => [
                  "rule" => ["checkFormatUserName", $data['username']],
                  "message" => "User Name không được chứa ký tự đặc biệt hoặc số!"
                ],
                "dk3" => [
                    "rule" => ["checkExistUserName", $data['username']],
                    "message" => "Bị trùng User Name mất rồi!",
                ],
            ],
            "email" => [
                "dk1" => [
                    "rule" => "notBlank",
                    "message" => "Hãy nhập Email!",
                ],
                "dk2" => [
                    "rule" => "/^[a-z A-Z]{1}[a-z A-Z 0-9_]+\@[a-z A-Z 0-9]{2,}\.[a-z A-Z]{2,}$/i",
                    "message" => "Hãy nhập đúng định dạng Email!",
                ],
                "dk3" => [
                    "rule" => ["checkExistEmail", $data['email']],
                    "message" => "Email bị dùng mất rồi!",
                ]
            ],
            "password" => [
                "dk1" => [
                "rule" => "notBlank",
                "message" => "Hãy nhập password!",
                    ],
                "dk2" => [
                    "rule" => ["checkRangePassword", $data['password']],
                    "message" => "Password trong khoảng từ 6-8 ký tự!"
                ]
            ],
            "passwordconfirm" => [
                "rule" =>  ["checkConfirmPassword", $data['password'], $data['passwordconfirm']],
                "message" => "Password và Confirm không trùng nhau!",
            ],

            "address" => [
                "dk1" => [
                "rule" => "notBlank",
                "message" => "Hãy nhập Address!",
                    ],
                "dk2" => [
                    "rule" => ["checkMaxLength", $data['address']],
                    "message" => "Address quá 50 ký tự!",
                ]
            ],
            "birthday" => [
                "dk1" => [
                "rule" => "notBlank",
                "message" => "Hãy nhập Birthday!",
                    ],
                "dk2" => [
                    "rule" => ["checkFormatDateTime", $data['birthday']],
                    "message" => "Birthday sai định dạng rồi!",
                ],
            ],
        ];

        if($this->validates($this->validate))
            return true;
        else
            return false;

    }

    /**
     * Validate khi edit user
     */
    public function validateEditUser($data)
    {

        $this->validate = [
            "email" => [
                "dk1" => [
                    "rule" => "notBlank",
                    "message" => "Hãy nhập Email!",
                ],
                "dk2" => [
                    "rule" => "/^[a-z A-Z]{1}[a-z A-Z 0-9_]+\@[a-z A-Z 0-9]{2,}\.[a-z A-Z]{2,}$/i",
                    "message" => "Hãy nhập đúng định dạng Email!",
                ],
                "dk3" => [
                    "rule" => ["checkExistEmail", $data['email']],
                    "message" => "Email bị dùng mất rồi!",
                ]
            ],
            "address" => [
                "dk1" => [
                    "rule" => "notBlank",
                    "message" => "Hãy nhập Address!",
                ],
                "dk2" => [
                    "rule" => ["checkMaxLength", $data['address']],
                    "message" => "Address quá 50 ký tự!",
                ]
            ],
            "birthday" => [
                "dk1" => [
                    "rule" => "notBlank",
                    "message" => "Hãy nhập Birthday!",
                ],
                "dk2" => [
                    "rule" => ["checkFormatDateTime", $data['birthday']],
                    "message" => "Birthday sai định dạng rồi!",
                ],
            ],
        ];

        if($this->validates($this->validate))
            return true;
        else
            return false;

    }

    /**
     * Kiểm tra password và password confirm có trùng nhau không
     * @param $password
     * @param $passwordConfirm
     *
     * @return bool
     */
    public function checkConfirmPassword($password, $passwordConfirm)
    {
        $password = $password['passwordconfirm'];
        if ($passwordConfirm != $password)
            return false;
        else
        return true;
    }

    /**
     * Kiểm tra format user name không được nhập ký tự đặc biệt và số
     * @param $username
     *
     * @return bool
     */

    public function checkFormatUserName($username)
    {
        $username = $username['username'];
        if (!preg_match("/^[a-zA-Z]+$/", $username))
            return false;
        else
        return true;
    }

    /**
     * Kiểm tra trùng user name trong database hay không
     * @param $username
     *
     * @return bool
     */
    public function checkExistUserName($username)
    {

        $user = $username['username'];
        $params = [
            'fields' => [
                'User.user_name',
                'User.password'
            ],
            'conditions' => [
                "User.user_name = \"$user\"",
            ]
        ];

        $countUser = $this->find('count', $params);
        if ($countUser != 0)
            return false;
        else
        return true;
    }

    /**
     * Kiểm tra trùng user name trong database hay không
     * @param $username
     *
     * @return bool
     */
    public function checkExistEmail($email)
    {
        $email = $email['email'];
        $params = [
            'fields' => [
                'User.email',
            ],
            'conditions' => [
                "User.email = \"$email\"",
            ]
        ];

        $countUser = $this->find('count', $params);
        if ($countUser != 0)
            return false;
        else
        return true;
    }

    /**
     * Kiểm tra khoảng range của Password
     * @param $password
     *
     * @return bool
     */
    public function checkRangePassword($password)
    {
        $stringPass = $password['password'];
        $count = strlen($stringPass);
        if ($count < 6 || $count > 8)
            return false;
        else
        return true;
    }

    /**
     * Hàm check max length ký tự để validate
     * @param $address
     *
     * @return bool
     */
    public function checkMaxLength($address)
    {
        $stringAddress = $address['address'];
        $count = strlen($stringAddress);
        if ($count > 50)
            return false;
        else
        return true;
    }

    /**
     * Định dạng format Birthday nhâp khi add
     * @param $datetime
     *
     * @return bool
     */
    public function checkFormatDateTime($datetime)
    {

        if (preg_match("/^(\d{2})-(\d{2})-(\d{4})$/", $datetime['birthday'])) {
            return true;
        } else {
            if (preg_match("/^(\d{2})\/(\d{2})\/(\d{4})$/", $datetime['birthday'])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kiểm tra có tồn tại username và password không khi login
     * @param $username
     * @param $password
     *
     * @return bool
     */
    public function checkExistUser($email, $password)
    {

        $this->useTable = true;
        $params = [
            'fields' => [
                'User.email',
                'User.password'
            ],
            'conditions' => [
                "User.email = \"$email\"",
                'AND' => [
                    "User.password = \"$password\"",
                ]
            ]
        ];

        $countUser = $this->find('count', $params);
        if ($countUser == 0)
            return false;
        else
            return true;
    }

    /**
     * Hàm get type của user login
     * @param $email
     * @param $password
     *
     * @return array|null
     */
    public function getTypeUser($email, $password)
    {
        $params = [
            'fields' => [
                'User.id',
                'UserType.id',
                'UserType.type_name'
            ],
            'conditions' => [
                "User.email = \"$email\"",
                'AND' => [
                    "User.password = \"$password\"",
                ]
            ]
        ];

        $userTypes = $this->find('all', $params);

        return $userTypes;
    }

    /**
     * Get all users
     * @param $Paginator
     * @param $mailSearch
     */
    public function getListUsers($Paginator, $mailSearch, $type)
    {

        $conds = [];  // tạo biến điều kiện tìm kiếm

        if ($type == 1) {
            if ($mailSearch != '') {
                $conds = ["User.email LIKE '%$mailSearch%'"];
            } else {
                $conds = [];
            }
        } else {
            $conds = [
                'AND' => [
                    "User.email LIKE '%$mailSearch%'",
                    "User.user_types_id = '$type'",
                ]
            ];
        }
        $Paginator->settings = ['limit' => 20,
            'fields' => [
                'User.id',
                'User.email',
                'User.user_name',
                'User.address',
                'User.birthday',
                'UserType.type_name',
            ],
            'conditions' => $conds
        ];

        // get count user
        $params = [
            'fields' => [
                'User.id',
            ],
            'conditions' => $conds
        ];

        // Đếm tổng user theo điều kiện search
        $count = $this->find('count', $params);

        $userLists = $Paginator->paginate('User');

        $userLists['count'] = $count; // tạo biến lưu giá trị total user

        return $userLists;
    }

    /**
     * Xử lý add user ở màn hình add
     * @param $dataAdd
     *
     * @return bool
     * @throws Exception
     */
    public function insertUser($dataAdd)
    {

        if ($dataAdd['type'] == "Admin") {
            $type = 1;
        } elseif ($dataAdd['type'] == "User") {
            $type = 2;
        }

        $dataInsert = [

            'user_types_id' => $type,
            'email' => $dataAdd['email'],
            'password' => $dataAdd['password'],
            'user_name' => $dataAdd['username'],
            'address' => $dataAdd['address'],
            'birthday' => date('Y-m-d', strtotime(str_replace('/', '-', $dataAdd['birthday']))),
        ];

        $this->validator()->remove('birthday');

        $this->set($dataInsert);

        if ($this->save())
           return true;
        return false;
    }

    /**
     * Xử lý logic update User vào database
     * @param $dataEdit
     *
     * @return bool
     * @throws Exception
     */
    public function updateUser($dataEdit)
    {
        if ($dataEdit['type'] == "Admin") {
            $type = 1;
        } elseif ($dataEdit['type'] == "User") {
            $type = 2;
        }
        $dataEdit = [
            'id' => $dataEdit['id'],
            'user_types_id' => $type,
            'email' => $dataEdit['email'],
            'user_name' => $dataEdit['username'],
            'address' => $dataEdit['address'],
            'birthday' => date('Y-m-d', strtotime(str_replace('/', '-', $dataEdit['birthday']))),
        ];

        $this->validator()->remove('birthday');

        $this->set($dataEdit);

        if ($this->save())
            return true;
        return false;
    }

    /**
     * Login xử lý xóa user
     * @param $dataDelete
     *
     * @return bool
     */
    public function deleteUser($dataDelete)
    {
       $this->id = $dataDelete['id'];
       if ($this->delete())
           return true;
       return false;
    }

    /**
     * Tìm kiếm user theo id
     * @param int $id
     *
     * @return array|null
     */
    public function findUserById($id = 0)
    {
        $conds = [];
        $user = [];
        if (is_numeric($id)) {
            $conds = [
              "User.id = $id",
            ];

            $params = [
                'fields' => [
                    'User.id',
                    'User.email',
                    'User.user_name',
                    'User.address',
                    'User.birthday',
                    'UserType.type_name',
                ],
                "conditions" => $conds,
            ];

            $user = $this->find('all', $params);
            return $user;
        } else {
            return null;
        }

    }

    /**
     * Tìm kiếm danh sách user sinh tháng truyền vào
     * @param $month_may
     *
     * @return array|null
     */
    public function findAllUserInMay($month_may)
    {

        $params = [
            'fields' => [
                'User.id',
                'User.email',
                'User.user_name',
                'User.birthday',
            ],
            "conditions" => [
                'MONTH(User.birthday)' => $month_may],
        ];
        $user = $this->find('all', $params);
        return $user;
    }

}
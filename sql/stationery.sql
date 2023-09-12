-- 创建文具类别表
CREATE TABLE Category
(
    CategoryID   INT PRIMARY KEY,      -- 类别ID
    CategoryName VARCHAR(50) NOT NULL, -- 类别名称
    Description  VARCHAR(200)          -- 描述
);

-- 创建供应商表
CREATE TABLE Supplier
(
    SupplierID    INT PRIMARY KEY,       -- 供应商ID
    SupplierName  VARCHAR(50)  NOT NULL, -- 供应商名称
    ContactPerson VARCHAR(50)  NOT NULL, -- 联系人
    ContactNumber VARCHAR(20)  NOT NULL, -- 联系电话
    Address       VARCHAR(100) NOT NULL  -- 地址
);
-- 创建商品表
CREATE TABLE Product
(
    ProductID     INT PRIMARY KEY,         -- 商品ID
    ProductName   VARCHAR(50)    NOT NULL, -- 商品名称
    CategoryID    INT            NOT NULL, -- 类别ID，关联到文具类别表中的类别ID
    SupplierID    INT            NOT NULL, -- 供应商ID，关联到供应商表中的供应商ID
    Price         DECIMAL(10, 2) NOT NULL, -- 价格
    StockQuantity INT            NOT NULL, -- 库存数量
    Description   VARCHAR(200),            -- 描述
    FOREIGN KEY (CategoryID) REFERENCES category (CategoryID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier (SupplierID)
);

-- 表的视图
CREATE
ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `category_view` AS
select `category`.`id` AS `编号`, `category`.`CategoryName` AS `类别名称`, `category`.`Description` AS `分类描述`
from `category`;
CREATE
ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `employee_view` AS
select `employee`.`id`       AS `编号`,
       `employee`.`name`     AS `名字`,
       `employee`.`gender`   AS `性别`,
       `employee`.`password` AS `密码`,
       `employee`.`phone`    AS `手机号码`
from `employee`;
CREATE
ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `product_view` AS
select `p`.`id`            AS `商品ID`,
       `p`.`ProductName`   AS `商品名称`,
       `c`.`CategoryName`  AS `类别名称`,
       `s`.`SupplierName`  AS `供应商名称`,
       `p`.`Price`         AS `价格`,
       `p`.`StockQuantity` AS `库存数量`,
       `p`.`Description`   AS `描述`
from ((`product` `p` join `category` `c` on ((`p`.`CategoryID` = `c`.`id`))) join `supplier` `s`
      on ((`p`.`SupplierID` = `s`.`id`)));
CREATE
ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `supplier_view` AS
select `supplier`.`id`            AS `编号`,
       `supplier`.`SupplierName`  AS `名字`,
       `supplier`.`ContactPerson` AS `联系人`,
       `supplier`.`ContactNumber` AS `联系电话`,
       `supplier`.`Address`       AS `地址`
from `supplier`;

-- 数据库自己写
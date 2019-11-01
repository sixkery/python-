## 题目
给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。

所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。

 

示例 1：
```java
输入：address = "1.1.1.1"
输出："1[.]1[.]1[.]1"
示例 2：

输入：address = "255.100.50.0"
输出："255[.]100[.]50[.]0"
```

提示：

给出的 address 是一个有效的 IPv4 地址

## 解答
1. 性能不好
```java
    class Solution {
        public String defangIPaddr(String address) {
            return address.replace(".", "[.]");
        }
    }
```
这样好些吧
```java
class Solution {
        public String defangIPaddr(String address) {
            StringBuffer buffer = new StringBuffer();
            for (char c : address.toCharArray()) {
                if ('.' == c) {
                    buffer.append("[.]");
                } else {
                    buffer.append(c);
                }
            }
            return buffer.toString();
        }
    }
```

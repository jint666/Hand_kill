# Hand_kill  
## 中文名：手杀  （游戏名来源，不受时间地点环境限制，有两只手即可开始）
### 1.0 version
- 源自于2016年，一直有想把它做成网络游戏的想法。
- 这是游戏的初始版本，2024年2月和占JW共同完成，一个小小的Java程序，一切都未完善。
- 算是第一个小制作？

### 游戏规则：
1. 每名玩家初始有10滴血，两个点数，都为“1”。
2. 游戏为回合制，每回合玩家可选择自己的一个点数与另一名玩家其中一个点数进行碰点，即自己的这个点数更新为自己点数加上对方点数，若超出9，则进行-10处理（不能碰对方的“0”）。
3. 每次碰点后玩家的特定点数会触发相应效果，且左右点数无顺序区别，具体效果如下（扣血扣对方，加血加自己，归一指将该点数变为“1”）：
   
    组合触发：
    - 0 0: 扣5血
    - 2 2: 扣1血
    - 4 4: 扣4血
    - 5 X (X=1/2/5): 扣X血
    - 6 X (X=1/2/3/4/8): 扣X/2血
                   (X=6): 扣5血
    - 7 7: 扣2血(若对方点数中含6，外加6归一，回1血效果)
    - 9 9: 扣2血 回2血
    - 3 3: 回2血
    - 3 8: 回2血
    - 8 8: 回2血
    
    单一触发(必须为新碰出的点数)：
    - 7: 效果一：扣1血(对方点数中无"6")
         效果二：回1血(对方点数中含"6"，则将6归一，回1血)
    - 9: 扣1血 回1血
    
    防御效果(受到伤害时触发)：
    - 当自己点数中有"0"，受到伤害时"0"归一，抵消0.5的伤害

4. 当一名玩家血量小于等于0时结束，对方胜利。
### 游戏玩法
![image](https://github.com/jint666/Hand_kill/assets/120358285/5ca10666-dab1-4c9c-9adc-414f9885d89c)
![image](https://github.com/jint666/Hand_kill/assets/120358285/a4b32877-8dad-4623-97a8-1b0cf142309b)
- 选项1：人机对战
- 选项2：双人模式
- 选项3：电子斗蛐蛐（玩家1随机碰点，玩家二策略碰点，大家可以根据自己的想法修改代码，两个玩家都随机碰点，这才是真正的电子斗蛐蛐！）
![image](https://github.com/jint666/Hand_kill/assets/120358285/2542a9bf-ec32-49a3-bfe9-877756affbc2)
- 例如上图，“1 1”代表用自己的第一个点数碰对方第一个点数。
![image](https://github.com/jint666/Hand_kill/assets/120358285/d8327f1d-9f24-42a9-85ed-11296734b01e)
- 例如上图，此时会打印出当前局势。
![image](https://github.com/jint666/Hand_kill/assets/120358285/6b5437f6-f17d-4ce3-b545-0ef398259382)
- 例如上图，机器选择最优碰点，四个数分别代表对机器而言的四种碰点势头（1 1、1 2、2 1、2 2碰点），接着显示机器碰点后的局势。
- 轮到你碰点，直到一方角色死亡游戏结束。



# Block Chain & Bitcoin

Links:
- [btc.com](https://btc.com/)
- [深入浅出区块链](https://learnblockchain.cn/2018/01/11/guide/#more)
- Wikipedia
- [区块链扫盲](https://blog.csdn.net/mazegong/article/details/79654220)

Bitcoin is an application of block chain technology.

## 1. Bitcoin Introduction & Fundamentals

### 1.1 What is Bitcoin?

- a cryptocurrency, the first decentralized digital currency
- invented by Satoshi Nakamoto 中本聪 in 2009
- rewarded by mining (the use of computer processing power)

### 1.2 Cryptography & Cyberpunks

[Cyperpunk: cryptography advocates](https://en.wikipedia.org/wiki/Cypherpunk) since late 1980s, defend privacy 
- Timothy C. May
- Eric Hughes
- John Gilmore 
- Hal Finney : an early Bitcoin user and received the first bitcoin transaction from Bitcoin's creator
- Julian Assange: WikiLeaks founder

### 1.3 Other Mainstream Cryptocurrencies

[List of Cryptocurrencies](https://en.wikipedia.org/wiki/List_of_cryptocurrencies)
- Ether/Ethereum: released in 2015 by Vitalik Buterin, featured by smart contract (scripting) functionality, Ethereum Virtual Machine (EVM) -  a decentralized Turing-complete virtual machine
From 2016, two sepearte blockchains:  Ethereum (ETH), and the original Ethereum Classic (ETC).
- Ripple: designed for peer to peer debt transfer, not based on Bitcoin.
- Litcoin : to GPU instead of CPU, higher efficiency in transaction confirmation (avg 2.5 mins)
- USDT: 1 USDT = $1USD
- EOS: block time of 500ms (fast), Feeless Smart contract platform. 

- BITMAIN 比特大陆：ASIC chips, bitcoin miner 吴忌寒 詹克团 owning 65% Bitcoin processing power, include direct ownership of BTC.com & AntPool, 矿池&交易所 "Bitcoin Valient"
net profit ~= Nvidia 30亿
- Bitcoin Cash / BCH: a hard branch of Bitcoin by ViaBTC

### 1.4 Common Uses of Bitcoin
- Payment (Dell)
- Fundraising / Public Assets
- Illegal Transactions 

### 1.5 Fundamental Concepts

#### Blockchain (data structure, DAG-directed acyclic graphs)
- a chain of Blocks: a continuously growing list of records
- Main chain, genesis block(the first block), orphan blocks(blocks outside of the main chain)
- Each block contains a SHA-256 cryptographic hash of the previous block

#### Block Structure (80 bytes)
block = transactions, individual pages of a city recorder's recordbook
- version - 4 bytes
- previous block hash - 32 bytes
- merkle root hash - 32 bytes
- timestamp - 4 bytes
- bits/difficulty value - 4 bytes
- nonce - 4 bytes

#### Blockchain vs linked list:
- linked to parent, but via hashes instead of pointers
- more complex data structure (use merkle trees to store the transactional data within a block, blocks linked via parent hashes)

#### Merkle Tree/Hash Tree (data structure, DAG): 
a tree in which every leaf node is labelled with the hash of a data block and every non-leaf node is labelled with the cryptographic hash of the labels of its child nodes. 
a generalization of hash lists(a list of hashes of the data blocks) and hash chains (a method to produce many one-time keys from a single key or password using hash functions)

#### Wallets
1. randomly pick a valid private key(256bits - NEED TO BE SAFELY STORED), 
2. compute the corresponding public key via SECP256K1 (the reverse is impossible)
3. compute the public key hash via SHA256 & RIPEMD160 (the reverse is impossible)
4. get head, get tail-verification code (via double SHA256) link head + public key hash + tail
5. get bitcoin wallet address via BASE58 (can be reversed)

#### Transaction
1. input: value of transaction, target wallet address 
2. use private key to digitally sign the transaction
3. calculate the output wallet address from private key 
4. broadcase the transaction (input & output wallet address, sign, value) to the network

#### Mining
a record-keeping service done through the use of computer processing power.

a new block must contain a so-called proof-of-work (PoW) - by Adam Back in 1997, hashcash.
Find a nonce (an arbitrary number that can only be used once) that when the block content is hashed along with the nonce, the result is numerically smaller than the network's difficulty target. Easy to verify, but hard to find the nonce; miners must try many different nonce values. The difficulty value is adjusted for every 2,016 blocks. 

approx 10 mins per block, gain 12.5 bitcoins per block, finish in 2140.  currently 520000 blocks. 

#### Cheating
if the transaction does not pass the verification (how?) by other nodes, the entire packaged block will be abandoned. 

#### Example of PoW:
```
"Hello, world!0" => 1312af178c253f84028d480a6adc1e25e81caa44c749ec81976192e2ec934c64
"Hello, world!1" => e9afc424b79e4f6ab42d99c81156d3a17228d6e1eef4139be78e948a9332a7d8
"Hello, world!2" => ae37343a357a8297591625e7134cbea22f5928be8ca2a32aa475cf05fd4266b7
...
"Hello, world!4248" => 6e110d98b388e77e9c6f042ac6b497cec46660deef75a55ebc7cfdf65cc0b965
"Hello, world!4249" => c004190b822f1669cac8dc37e761cb73652e7832fb814565702245cf26ebb9e6
"Hello, world!4250" => 0000c3af42fc31103f1fdc0151fa747ff87349a4714df7cc52ea464e12dcd4e9
```
Until the 4251th calculation to get a hash value with beginning 4 0's. 
伪随机 Pseudorandomness

## 2. Block Chain Technology

### 2.1 P2P Network
The interconnected nodes ("peers") share resources amongst each other without the use of a centralized administrative system. 

#### Napster Model
Napster: a music-sharing app using P2P file sharing, first released in 1999. Became an online music store because of copyright infringement, and was acquired on 2011. Founded by Shawn Fanning and Sean Parker. 

#### Gnutella Model
Gnutella (GNU Project) is a large peer-to-peer network, released in 2000. Used by millions for for peer-to-peer file sharing. Firstly developed by Justin Frankel and Tom Pepper of Nullsoft, soon after the company's acquisition by AOL(a web portal and online service provider). 

#### BlockChain & P2P
[深入浅出比特币 - 分析比特币网络：一种去中心化、点对点的网络架构](https://learnblockchain.cn/2017/11/07/bitcoin-p2p/)
拓扑结构，网络连接，节点连接/节点协议

* Nodes: flat connections among each other, no server 

* To join the network, a bitcoin client will do the following: 
1. remember the closest network node, will connect to it after reboot 
2. when a node loses its connection, it will try to find new nodes to connect
3. when a node builds its connection, it will send information including its IP to connected nodes, and the nodes will resend to other nodes to assure stability of the connection. 
4. A new node will send getaddr information to its connected nodes, and request them to return IP list of equivalent nodes, in order to connect with equivalent nodes.
5. When starting up a node, can assign the node with an active IP. If not, the client still maintains a list of stable nodes (种子节点: in order to quickly connect to other nodes).

* Bitcoin nodes usually use TCP Protocal & 8333 port to connect with other nodes. 
---
lookup bitcoin hosts  (bitcoin 8333) (ethereum port 80303)
nslookup seed.bitcoin.sipa.be
nc -nvv 159.65.106.136 8333
---

PED分布式算法

### 2.2 共识机制 Consensus Algorithm
谁有权利，作弊问题
[共识机制](https://yeasy.gitbooks.io/blockchain_guide/content/bitcoin/consensus.html)
[共识算法（POW,POS,DPOS,PBFT）介绍和心得](https://blog.csdn.net/lsttoy/article/details/61624287)

#### POW: Proof of Work 工作证明
Hash(block_header) < Target 
- 不环保：挖矿只有1/100有收益，其余99人被浪费了

#### POS: Proof of Stake 股权证明
Hash(block_header) < Target * CoinAge 币龄 + 长的币产生利息
解决POW的资源消耗问题
POW POS非拜占庭博弈；

#### Ripple Consensus  瑞波共识机制
Centralized, 51% power

#### DPOS: Delegated Proof of Stake 委任权益证明
让每一个持有比特股的人进行投票，由此产生21个节点，可以计票, 议会制度或人民代表大会制度

## 3. Industry Prospects
### 3.1 比特币产业
上游：生产、矿机 中国挖矿世界领先
CPU -> GPU -> FPGA-ASIC 
FPGA-ASIC是GPU的188倍，是cpu的34万倍.

Mining Pool Payment Methods 挖矿模式
PPS (Pay Per Share) VS.PPLNS(Pay Per Last N Shares)
a way of determining how many cryptocoins you get for your shares completed

中游：交易、储存
交易：
Over-The-Counter (OTC) 场外交易 VS Exchange 交易所交易

存储：
全节点
轻钱包

### 3.2 不同国家的政策
[世界各国是如何处理比特币的？](http://www.sohu.com/a/193525451_825310)
德国：鼓励、积极，世界首个承认比特币合法地位的国家 承认货币
法国：警告，不禁止
美国：不承认是货币，承认是商品 （期货委员会、商品委员会 -> 属于商品）
日本：对交易所严格、防止洗钱
俄罗斯：13年禁止（比特币持有非法），转变积极，现有官方批准的交易所
中国：禁止，墙 

### 3.3 比特币价格变化
[比特币王国的内战与分裂 by 小晚](https://blog.csdn.net/wowotuo/article/details/78417491) 

链接：
mygost
[Bitcoin Price](https://www.coindesk.com/price/)

1. 被发现 上涨 到$200
1. 15-16年寒冬 mygost被盗， $800 -> $200
2. 17年 中国 $6000
3. 17年11月 $20000
4. 18.1 下跌 $
5. 18.5 $8000

### 3.4 分支与扩容

核心开发团队 Core 控制权利&权威，1M区块大小，上层使用隔离见证，多搭闪电网络解决拥堵，比特币协议开发者blockstream公司可以收取侧链、闪电网络的专利手续费。

纽约协议派：Bitmain比特大陆：希望官方进行区块扩容

硬分叉：节点连接，发现节点不兼容时 拒绝连接，形成两个链/p2p网络
软分叉：进行扩容， 可以兼容2.0


## 4.实战

TBC:
How to start a Bitcoin wallet and make connections (internet?) - Simplified Payment Verification
How to start mining?
Use python to write block chains.


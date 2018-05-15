# Block Chain & Bitcoin

Links:
[btc.com](https://btc.com/)
[深入浅出区块链](https://learnblockchain.cn/2018/01/11/guide/#more)

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
- Bitcoin Cash / BCH: a hard branch of Bitcoin by ViaBTC

### 1.4 Common Uses of Bitcoin
- Payment (Dell)
- Fundraising / Public Assets
- Illegal Transactions 

### 1.5 Fundamental Concepts

- Blockchain (data structure, DAG-directed acyclic graphs)
a chain of Blocks: a continuously growing list of records
Main chain, genesis block(the first block), orphan blocks(blocks outside of the main chain)
Each block contains a SHA-256 cryptographic hash of the previous block

- Blockchain vs linked list:
linked to parent, but via hashes instead of pointers
more complex data structure (use merkle trees to store the transactional data within a block, blocks linked via parent hashes)

- Merkle Tree/Hash Tree (data structure, DAG): 
a tree in which every leaf node is labelled with the hash of a data block and every non-leaf node is labelled with the cryptographic hash of the labels of its child nodes. 
a generalization of hash lists(a list of hashes of the data blocks) and hash chains (a method to produce many one-time keys from a single key or password using hash functions)

- Wallets
1. randomly pick a valid private key(256bits - NEED TO BE SAFELY STORED), 
2. compute the corresponding public key via SECP256K1 (the reverse is impossible)
3. compute the public key hash via SHA256 & RIPEMD160 (the reverse is impossible)
4. get head, get tail-verification code (via double SHA256) link head + public key hash + tail
5. get bitcoin wallet address via BASE58 (can be reversed)

- Transaction
1. input: value of transaction, target wallet address 
2. use private key to digitally sign the transaction
3. calculate the output wallet address from private key 
4. broadcase the transaction (input & output wallet address, sign, value) to the network

- Mining
a record-keeping service done through the use of computer processing power.

a new block must contain a so-called proof-of-work (PoW) - by Adam Back in 1997, hashcash.
Find a nonce (an arbitrary number that can only be used once) that when the block content is hashed along with the nonce, the result is numerically smaller than the network's difficulty target. Easy to verify, but hard to find the nonce; miners must try many different nonce values. The difficulty value is adjusted for every 2,016 blocks. 

approx 10 mins per block, gain 12.5 bitcoins per block, finish in 2140.  currently 520000 blocks. 

- Cheating: if the transaction does not pass the verification (how?) by other nodes, the entire packaged block will be abandoned. 

- Block Structure (80 bytes)
block = transactions, individual pages of a city recorder's recordbook
1. version - 4 bytes
2. previous block hash - 32 bytes
3. merkle root hash - 32 bytes
4. timestamp - 4 bytes
5. bits/difficulty value - 4 bytes
6. nonce - 4 bytes

Example of PoW:
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


TBC:
How to start a Bitcoin wallet and make connections (internet?) - Simplified Payment Verification
How to start mining?
Use python to write block chains.



## Data Types

- `Any` - a supertype of any other type
- `Unit` - a type with a single value `()`
- `Int` - 64 bit signed integer
- `Bool` - a type with two logical values `true` and `false`
- `String` - arbitrary sequence of chars
- `Array[T]` - arrays of arbitrary length with all values of type `T`

## Syntax

    // Constant definition
    let a: Int = 10                       // Explicit type annotation
    let b = 100                           // Type will be inferred automatically
    let c = if (a > b) true else false    // Conditional assignment

    // Function definition
    def sum(a: Int, b: Int): Int = {
        a + b
    }
    
    // Lambda definition
    lamb (a: Int, b: Int) = a + b

    // If statement
    let flag: Bool = if (10 < 100) {
        true
    } else {
        false
    }
    
    // Type matching
    let validProof: Bool = if (let sig: Signature25519 = poof) checkSig(sig, msg, pk) else false

    // Base58 string
    let pubKeyBytes: Array[Byte] = base58'75Gs7HHUNnoEzsPgRRVABzQaC3UZVcayw9NY457Kx5p'
    
    // Byte
    let byte: Byte = (127).toByte

    // Collections
    let ageList: Array[Int] = List(1, 2, 3, 4)
    let ageDict: Dict[String, Int] = Dict('Alice' -> 4, 'Bob' -> 9, 'Tom' -> 17) [Under discussion]

    // Collection subscription
    let someonesAge = ageList[0]            // Will result in `1`

    // Lambda application
    let doesExist: Bool = ageList.exists(lamb (i: Int) = i > 3)             // true
    let ageListDoubled: Array[Int] = ageList.map(lamb (i: Int) = i * 2)     // Array(2, 4, 6, 8)

## Contract Example

    contract (signature: Signature25519) {
        let ownerPubKey = base58"GtBn7qJwK1v1EbB6CZdgmkcvt849VKVfWoJBMEWsvTew"
        checkSig(ctx.transaction.msg, ownerPubKey, signature)
    }

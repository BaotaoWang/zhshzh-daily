import CryptoJS from 'crypto-js'

/**
 * 对字符串用crypto算法进行加密，当前只支持对字符串进行加密
 * @param value 要加密的字符串
 * @param key 密钥，必须是16位十六进制的字符
 * @param iv 偏移量，必须是16位十六进制的字符
 * @returns {string}
 */
const encrypt = (value, key, iv) => {
  if (value) {
    // 字符串加密
    let encryptedValue = CryptoJS.enc.Utf8.parse(value)
    // 密钥加密
    let encryptedKey = CryptoJS.enc.Utf8.parse(key)
    // 偏移量加密
    let encryptedIv = CryptoJS.enc.Utf8.parse(iv)
    // AES加密
    let encrypted = CryptoJS.AES.encrypt(encryptedValue, encryptedKey, {
      iv: encryptedIv,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7
    })
    return encrypted.ciphertext.toString().toUpperCase()
  } else {
    return ''
  }
}

/**
 * 对用crypto算法加密后的字符串进行解密
 * @param value 加密后的字符串
 * @param key 密钥，必须是16位十六进制的字符
 * @param iv 偏移量，必须是16位十六进制的字符
 * @returns {string}
 */
const decrypt = (value, key, iv) => {
  if (value) {
    let encryptedHexStr = CryptoJS.enc.Hex.parse(value)
    let encryptedKey = CryptoJS.enc.Utf8.parse(key)
    let encryptedIv = CryptoJS.enc.Utf8.parse(iv)
    let baseResult = CryptoJS.enc.Base64.stringify(encryptedHexStr)
    // AES解密
    let decrypt = CryptoJS.AES.decrypt(baseResult, encryptedKey, {
      iv: encryptedIv,
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7
    })
    return CryptoJS.enc.Utf8.stringify(decrypt)
  } else {
    return ''
  }
}

export {
  encrypt,
  decrypt
}

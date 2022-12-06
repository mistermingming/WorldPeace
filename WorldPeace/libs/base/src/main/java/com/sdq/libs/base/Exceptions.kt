package com.sdq.libs.base

/**
 *
 * @PackageName com.sdq.libs.base
 * @date 2022/12/6 15:21
 * @author songdongqi
 */
/**
 * 业务域。用于日志输出，异常，数据上报等场景区分信息来源。
 * @param id 业务编号。范围：100以内为保留域； 100-1000为sdk域； 1000以上为可自定义业务域。
 */
data class Domain(val name: String, val id: Int)

abstract class BaseException : Exception {
    /**
     * 业务域。
     */
    val domain: Domain

    /**
     * 错误码。
     * 错误来源+四位数字编号，例如A0001，B0001，C0001
     * A来源于用户，B来源于系统，C来源于第三方
     */
    val code: String

    /**
     * 异常信息。
     */
    final override val message: String

    constructor(domain: Domain, code: String, message: String) : super() {
        this.message = message
        this.domain = domain
        this.code = code
    }

    constructor(domain: Domain, code: String, message: String, cause: Throwable?)
            : super(cause) {
        this.message = message
        this.domain = domain
        this.code = code
    }

    override fun toString(): String {
        return "${super.toString()}  [${domain.name}]  (code $code)"
    }

}
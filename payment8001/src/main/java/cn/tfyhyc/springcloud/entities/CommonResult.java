package cn.tfyhyc.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 头发又黑又长
 * @Date 2022/10/2 23:19
 * @email zwb15083976291@163.com
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

}

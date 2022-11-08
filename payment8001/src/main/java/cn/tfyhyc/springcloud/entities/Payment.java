package cn.tfyhyc.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 头发又黑又长
 * @Date 2022/10/2 23:13
 * @email zwb15083976291@163.com
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment implements Serializable {
    private Long id;
    private String serial;
}

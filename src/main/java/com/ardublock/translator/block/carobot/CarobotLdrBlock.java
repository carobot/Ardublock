package com.ardublock.translator.block.carobot;

import com.ardublock.translator.Translator;

public class CarobotLdrBlock extends AbstractCarobotReadAnalogBlock {
  public CarobotLdrBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix,
      String label) {
    super(blockId, translator, codePrefix, codeSuffix, label);
  }

}

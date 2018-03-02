package com.ardublock.translator.block.carobot;

import com.ardublock.translator.Translator;

public class CarobotLedPwmBlock extends AbstractCarobotWriteAnalogBlock {
  public CarobotLedPwmBlock(Long blockId, Translator translator, String codePrefix,
      String codeSuffix, String label) {
    super(blockId, translator, codePrefix, codeSuffix, label);
  }

}

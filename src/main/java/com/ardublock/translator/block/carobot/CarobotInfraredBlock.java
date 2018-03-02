package com.ardublock.translator.block.carobot;

import com.ardublock.translator.Translator;

public class CarobotInfraredBlock extends AbstractCarobotReadAnalogBlock {
  public CarobotInfraredBlock(Long blockId, Translator translator, String codePrefix,
      String codeSuffix, String label) {
    super(blockId, translator, codePrefix, codeSuffix, label);
  }

}

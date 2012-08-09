package org.alfresco.extendedencryption.aeem;

/*
 * Project: Alfresco Encryption Extension Module , part of the Creative Summer
 * Dự án : Mở rộng module mã hóa cho alfresco , nằm trong mùa hè sáng tạo   
 * 
 * This code was developped by a group of 3 students from UET-VNU .
 * Dự án được phát triển bởi 1 nhóm sinh viên Đại học công nghệ - Đại học Quốc Gia Hà Nội 
 * 
 * License   : GNU General Public License, version 2 (http://www.gnu.org/licenses/gpl-2.0.html)
 * Giấy phép : GNU GPL 2.0 (nguồn : http://www.gnu.org/licenses/gpl-2.0.html )
 */

/**
 * decryption data in alfresco Giải mã tài liệu trong alfresco
 * 
 * @since 4.0 phiên bản 4.0 trở lên
 */

public class DecryptionExecuter extends BaseExecuter {

	/**
	 * Extend class BaseExecuter and Override void action to encrypt data 
	 * Thừa kế từ lớp Base và Override hàm action để giải mã dữ liệu
	 *
	 */

	@Override
	public void action(NodeRef nodeRefer) {
		byte[] data = AES.decrypt(super.getNodeContent(nodeRefer), null);
		super.write(nodeRefer, data);
	}
}

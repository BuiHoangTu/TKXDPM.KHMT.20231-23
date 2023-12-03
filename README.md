# TKXDPM.KHMT.20231-Group23

## Table of contents
- [TKXDPM.KHMT.20231-Group23](#tkxdpmkhmt20231-group23)
  - [Table of contents](#table-of-contents)
  - [Group members](#group-members)
  - [What's included](#whats-included)
  - [Report Content](#report-content)
  - [AIMS' description](#aims-description)

## Group members
| Name         | Role        |
| :----------- | :---------- |
| Bùi Hoàng Tú | Team Leader |
|Nguyễn Tiến Tuấn| Member|

## What's included

The recommended structure is as follows:

- `AIMS`: folder containing the team's AIMS base code
- `assets`: folder containing images that you want to include in the report file
- `README.md`: weekly report file, individuals are required to update their tasks and how their do it into this file
- `Template.md`: template for the weekly report
- `pull_request_template.md`: pull request's description template


## Report Content

<details>
  <summary> W9: 28/11/2023~03/12/2023 </summary>
  <br>
  <!-- Member 1 -->
  <details>
    <summary>Team Member 1</summary>
    <br>

    - Assigned tasks:
      - Task 1
      - Task 2
      - ...

    - Implementation details:
      - Pull Request(s): [Attach links to your pull requests here. You can attach multiple pull requests]()
      - Specific implementation details:
        - Describe specific in detail what you did last week
        - You can attach images if you want

  </details>

  <!-- Member 2 -->
  <details>
    <summary>Team Member 2</summary>
    <br>

    - Assigned tasks:
      - Task 1
      - Task 2
      - ...
      - Đánh giá tính coupling của IPayOrderService, PayOrderService, PlaceOrderService

    - Implementation details:
      - Pull Request(s): [Attach links to your pull requests here. You can attach multiple pull requests]()
      - Specific implementation details:
        - Describe specific in detail what you did last week
        - You can attach images if you want

  </details>

  <!-- Member 3 -->
  <details>
    <summary>Team Member 3</summary>
    <br>

    - Assigned tasks:
      - Task 1
      - Task 2
      - ...

    - Implementation details:
      - Pull Request(s): [Attach links to your pull requests here. You can attach multiple pull requests]()
      - Specific implementation details:
        - Describe specific in detail what you did last week
        - You can attach images if you want

  </details>

  <!-- Nguyễn Tiến Tuấn -->
  <details>
    <summary>Team Member 4</summary>
    <summary>Nguyễn Tiến Tuấn</summary>
    <br>

    - Assigned tasks:
      - Task 1
      - Task 2
      - ...

    - Implementation details:
      - Pull Request(s): [Attach links to your pull requests here. You can attach multiple pull requests]()
      - Specific implementation details:
        - Describe specific in detail what you did last week
        - You can attach images if you want
        - IPayOrderService: control coupling vì các phương thức phụ thuộc vào các thông tin về thẻ tín dụng để kiểm soát luồng hoạt động của quá trình thanh toán,
           Sự phụ thuộc này được coi là loại control coupling vì các phương thức trong interface này đang kiểm soát việc xác thực thông tin của thẻ tín dụng.
        - PayOrderService: data coupling vì phụ thuộc vào các lớp 'Card' để thực hiện các chức năng xác thực, tạo ra một mức độ coupling 
          thông qua việc chia sẻ dữ liệu hoặc sử dụng các phương thức của class khác để thực hiện công việc của mình
        - PlaceOrderService: data coupling vì nó cung cấp các phương thức để kiểm tra số điện thoại, kiểm tra số lượng sản phẩm trong giỏ hàng và kiểm tra tên, sử dụng thông tin từ IDatabase và Cart

  </details>
  

</details>

---


## AIMS' description

Con đường tới tri thức, nghệ thuật, và giải trí đã, đang, và sẽ luôn là một phần cuộc sống của mỗi 
con người, thế nhưng, cuộc sống vốn không dễ dàng. Sẽ có lúc mà sản phẩm của sức lao động 
sáng tạo kia không thể đến với mọi người, chỉ vì những đứa con tinh thần không thể chu cấp cho 
họ - những văn nghệ sĩ, trí thức – một mức sống tối thiểu. May thay, khó khăn không làm ta chùn 
bước. Thời đại Internet bùng nổ, cùng với cuộc Cách mạng Công nghiệp 4.0, đã mang đến cơ hội 
mới cho tất cả chúng ta: AIMS Project, một phần mềm thương mại điện tử (E-commerce) chuyên 
về mua bán sản phẩm phương tiện truyền thông.

AIMS Project là một phần mềm trên desktop hoạt động 24/7, cho phép người dùng mới có thể
làm quen dễ dàng. Phần mềm này có thể cho phép phục vụ 1.000 khách hàng cùng lúc mà hiệu 
suất không bị giảm đáng kể, đồng thời có thể hoạt động 300 giờ liên tục không hỏng hóc. Ngoài 
ra, phần mềm có thể hoạt động trở lại bình thường trong vòng 1 giờ sau khi xảy ra lỗi. Thời gian 
đáp ứng của phần mềm tối đa là 2 giây khi bình thường hoặc 5 giây lúc cao điểm.

Trong phần mềm thương mại điện tử AIMS Project, người quản lý sản phẩm có thể thêm, xem, 
sửa, xóa bất kỳ sản phẩm nào. Tuy nhiên, người quản lý sản phẩm chỉ có thể thêm hoặc sửa với 
một sản phẩm tại một thời điểm, nhưng lại có thể xóa tới 10 sản phẩm cùng một lúc. Ngoài ra, 
người quản lý sản phẩm không thể xóa hoặc cập nhật quá 30 sản phẩm ví lý do bảo mật nhưng 
có thể thêm không giới hạn số sản phẩm trong một ngày.

Khi muốn thêm một sản phẩm để bán, người quản lý sản phẩm cần cung cấp thông tin mà phần 
mềm yêu cầu. Với mỗi sản phẩm truyền thông phương tiện (media), người quản lý sản phẩm cần 
cung cấp tên sản phẩm(title), loại hình (category), giá trị (value), và giá cả hiện tại (price). Giá trị
và giá cả của sản phẩm chưa bao gồm thuế giá trị gia tăng (VAT) 10%. Tùy thuộc vào các sản 
phẩm, người quản lý sản phẩm cần cung cấp thông tin như sau:

  - Sách quyển (book) yêu cầu cần có thông tin về tên các tác giả (authors), loại bìa (bìa mềm – paperback hoặc bìa cứng – hardcover), nhà xuất bản (publisher), và ngày xuất bản (publication date); ngoài ra, có thể có thêm thông tin số trang (pages), ngôn ngữ (language), và thể loại (ví dụ: truyện tranh, truyện chữ, sách ảnh).
  - Đĩa CD (compact disc) chứa bộ sưu tập nhạc, hay Album CD, yêu cầu cần có thông tin về tên của nghệ sĩ (artists), hãng ghi âm (record label), danh sách bài hát (tracklist), và thể loại (ví dụ: K-pop, C-pop, US-UK); ngoài ra, có thể có thêm thông tin ngày phát hành.
  - Đĩa than LP (long-play record) yêu cầu cần có các thông tin giống với đĩa CD
  - Đĩa DVD (digital video disc) yêu cầu cần có thông tin về loại đĩa (Blu-ray, HD-DVD), đạo diễn (director), thời lượng (runtime), hãng sản xuất (studio), ngôn ngữ, và phụ đề (subtitles); ngoài ra, có thể có thêm ngày phát hành, và thể loại (ví dụ: phim lẻ, phim bộ).

Hiện tại, phần mềm chỉ cho phép mua bán sản phẩm phương tiện truyền thông vật lý (physical 
good). Với những sản phẩm dạng này, cần có mã vạch barcode, mô tả sản phẩm (description, ví 
dụ: hàng mới, hàng cũ, màu chủ đạo, điều kiện hoàn trả), số lượng sản phẩm (quantity), ngày 
nhập kho, kích thước và khối lượng (product dimensions). Sản phẩm vật lý có thể là sách quyển, 
đĩa CD, đĩa than LP, hay đĩa DVD.

Giá cả (price) của sản phẩm có thể thay đổi phụ thuộc vào nhu cầu thị trường, do đó, người quản 
lý sản phẩm có thể cập nhật giá cả của một sản phẩm tối đa 02 lần trong một ngày. Tuy nhiên, 
giá cả của sản phẩm luôn cần nằm trong khoảng từ 30% đến 150% giá trị sản phẩm (value) để
tránh tình trạng thổi giá hoặc bán phá giá.

Phần mềm sẽ lưu trữ lịch sử các thao tác thêm, sửa, xóa sản phẩm và sẽ thông báo cho người 
quản lý sản phẩm nếu thao tác nào không hợp lệ. Ví dụ, khi đầu vào để thêm sản phẩm không 
tuân thủ quy tắc về giá trị và giá cả, hoặc sai định dạng ngày tháng.

Quản trị viên có thể tạo người dùng mới, xem thông tin người dùng, cập nhật thông tin người 
dùng, xóa người dùng và đổi mật khẩu. Quản trị viên có thể chặn người dùng, bỏ chặn người 
dùng khi cần. Phần mềm sẽ tự động gửi email tới người dùng để thông báo các hành động quản 
trị người dùng trên. Quản trị viên có thể thiết lập hoặc thay đổi vai trò của người dùng. Một người 
dùng có thể có nhiều vai trò khác nhau (cụ thể ở đây có hai vai trò là là quản trị viên hoặc người 
quản lý sản phẩm). Quản trị viên và người quản lý cần đăng nhập để vào sử dụng các tính năng 
tương ứng với vai trò của mình.

Về phía khách hàng, khi khởi động, phần mềm sẽ hiện ra danh sách của 20 sản phẩm bất kỳ ở
mỗi trang. Để tìm kiếm sản phẩm, khách hàng sử dụng các đặc tính của sản phẩm để tìm kiếm. 
Phần mềm sẽ hiện ra 20 sản phẩm liên quan trong mỗi trang tìm kiếm. Bên cạnh đó, khách hàng 
có thể sắp xếp sản phẩm theo giá cả hoặc có thể thêm sản phẩm với số lượng tương ứng vào giỏ
hàng (cart) hiện tại.

Khi xem giỏ hàng, phần mềm sẽ hiện ra thông tin giỏ hàng, bao gồm tổng giá cả sản phẩm chưa 
bao gồm VAT, tổng giá cả sản phẩm đã bao gồm VAT, danh sách sản phẩm với thông tin sản phẩm 
(tên sản phẩm, số lượng, và giá cả). Đồng thời, phần mềm cũng thông báo tới khách hàng nếu số
lượng hàng tồn trong kho bất kỳ sản phẩm không đủ và sẽ hiện ra số lượng này của từng sản 
phẩm bị thiếu. Khi thay đổi ý định, khách hàng cũng có thể bỏ sản phẩm khỏi giỏ hàng. Ngoài ra, 
chỉ có 1 giỏ hàng trong mỗi phiên bản chạy của phần mềm, đồng thời, giỏ hàng sẽ được làm trống 
sau khi thanh toán đơn hàng thành công.

Để đặt hàng thành công, khách hàng cần tiến hành đặt hàng và thanh toán. Trong phạm vi môn 
học, khách hàng không cần đăng nhập cũng có thể đặt hàng. Khách hàng khi đặt hàng cần cung 
cấp các thông tin giao hàng (delivery info bao gồm tên người nhận, email, số điện thoại, 
tỉnh/thành phố nhận hàng, và địa chỉ nhận hàng), thông tin thanh toán.

Trước khi đặt hàng, khách hàng cần xem giỏ hàng và lựa chọn các sản phẩm muốn mua. Từ đây,
khách hàng có thể yêu cầu đặt hàng. Mỗi khi khách hàng yêu cầu đặt hàng, phần mềm sẽ luôn 
kiểm tra xem lượng hàng tồn kho có đủ để cung cấp cho khách hàng. Phần mềm sẽ yêu cầu khách 
hàng cập nhật lại giỏ hàng nếu như lượng hàng tồn kho không đủ và sẽ hiện ra số lượng hàng tồn 
kho với mỗi sản phẩm không đáp ứng. Sau khi cập nhật giỏ hàng, khách hàng có thể yêu cầu đặt 
hàng lại.

Tiếp theo, phần mềm sẽ yêu cầu khách hàng thiết lập thông tin giao hàng (delivery info). Trong 
quá trình nhập thông tin giao hàng, khách hàng vẫn nhìn thấy các sản phẩm và thông tin như
trong giỏ hàng. Mỗi khi khách hàng nhập hoặc cập nhật địa chỉ giao hàng xong, phần mềm sẽ tính 
(lại) vphí giao hàng, tổng tiền hàng bao gồm phí giao hàng. Lúc này, phần mềm sẽ hiển thị và lưu 
lại thông tin hoá đơn tạm thời (invoice), gồm có danh sách sản phẩm trong giỏ hàng, số lượng, 
giá cả sản phẩm, tổng giá cả sản phẩm chưa bao gồm VAT, tổng giá cả sản phẩm đã bao gồm VAT, 
phí giao hàng, và tổng số tiền phải trả. Tổng số tiền khách hàng phải trả bao gồm tổng giá cả sản 
phẩm đã bao gồm VAT và phí giao hàng. Sau đó, phần mềm sẽ cho phép khách hàng thanh toán 
đơn hàng. Lúc này, phần mềm sẽ kiểm tra các thông tin đầu vào và yêu cầu khách hàng cập nhật 
lại nếu như có trường bắt buộc bị bỏ trống hoặc thông tin không hợp lệ.

Khách hàng có thể chọn phương thức giao hàng nhanh (rush order). Giao hàng nhanh cho phép 
khách hàng nhận hàng với thời gian hẹn trước trong vòng 2 tiếng. Hiện tại, chỉ địa chỉ trong nội 
thành Hà Nội được hỗ trợ giao hàng nhanh. Trong trường hợp khách hàng muốn giao hàng nhanh, 
phần mềm kiểm tra xem địa chỉ nhận hàng hỗ trợ giao hàng nhanh không, có sản phẩm nào được
hỗ trợ giao hàng nhanh không. Nếu không có sản phẩm nào hỗ trợ hoặc địa chỉ nhận hàng không 
hỗ trợ, phần mềm sẽ thông báo để khách hàng cập nhật lại thông tin giao hàng hoặc phương 
thức giao hàng. Trong trường hợp có sản phẩm hỗ trợ và địa chỉ giao hàng cũng hỗ trợ giao hàng 
nhanh, phần mềm sẽ hiển thị thông tin giao hàng, và yêu cầu khách hàng bổ sung thêm thông tin 
giao hàng nhanh (thời gian nhận hàng cho giao hàng nhanh, chỉ dẫn giao hàng) vào thông tin giao 
hàng, và đồng thời hiển thị cho khách hàng biết:
  - Nếu chỉ một số sản phẩm hỗ trợ giao hàng nhanh, các sản phẩm hỗ trợ giao nhanh này sẽ được giao cùng nhau tới địa điểm nhận hàng cho giao hàng nhanh vào thời điểm đã hẹn trước. Các sản phẩm không hỗ trợ giao hàng nhanh sẽ được giao như bình thường. Phí giao hàng sẽ được tính và hiển thị cùng nhóm sản phẩm này.
  - Nếu tất cả sản phẩm trong đơn hàng đều hỗ trợ giao hàng nhanh, các sản phẩm này sẽ được giao cùng nhau tới địa điểm nhận hàng cho giao hàng nhanh vào thời điểm đã hẹn trước. Phí giao hàng sẽ được tính và hiển thị cùng nhóm sản phẩm này.

Phí giao hàng sẽ phụ thuộc vào khối lượng sản phẩm và địa điểm nhận hàng.
  - Chi phí giao hàng sẽ không bị tính thuế.
  - Đơn hàng có tổng giá cả các sản phẩm đạt trên 100.000VNĐ sẽ được miễn phí vận chuyển.
  - Chỉ tính phí giao hàng với sản phẩm có khối lượng lớn nhất.
  - Nếu khách hàng ở nội thành TP. Hà Nội hoặc nội thành TP.HCM, giá khởi điểm cho 3kg đầu là 22.000VNĐ.
  - Nếu khách hàng ở vị trí khác trong lãnh thổ Việt Nam, giá khởi điểm cho 0,5kg đầu là 30.000VNĐ.
  - Cứ 0,5kg tiếp theo, khách hàng sẽ phải trả thêm 2.500VNĐ.
  - Trong trường hợp khách hàng chọn đặt hàng nhanh, khách hàng sẽ trả thêm 10.000VNĐ với mỗi sản phẩm giao hàng nhanh.

Trong bước thanh toán đơn hàng, khách hàng cần chọn phương thức thanh toán. Khách hàng có 
thể thanh toán qua các kênh thanh toán được hỗ trợ tại cổng thanh toán VNPay. Với từng hình 
thức thanh toán, khách hàng sẽ cần cung cấp các thông tin cần thiết theo yêu cầu của VNPay để
có thể thực hiện giao dịch thành công.

Sau khi thanh toán thành công, phần mềm sẽ hiển thị mã giao dịch (transaction ID), tên chủ thẻ, 
số tiền bị trừ, nội dung giao dịch, ngày giờ giao dịch. Đơn hàng sẽ vào trạng thái chờ xử lý và phần 
mềm sẽ gửi toàn bộ thông tin đơn hàng và thông tin giao dịch tới email của khách hàng. Lưu ý, 
khách hàng có thể thể quay lại bất kỳ bước nào hoặc thoát khỏi phần mềm trong quá trình đặt 
hàng. Cuối cùng, phần mềm ghi lại thông tin giao dịch và đơn hàng đã được thanh toán thành 
công.

Khách hàng có thể xem lại thông tin đơn hàng, hoặc huỷ đơn hàng theo đường link gửi trong 
email về đơn hàng và giao dịch. Khách hàng có thể xem toàn bộ thông tin về đơn hàng, bao gồm 
hoá đơn, thông tin giao hàng và thông tin giao dịch thanh toán đơn hàng khi ấn vào đường link 
đó. Khách hàng có thể chọn huỷ đơn hàng khi xem thông tin về đơn hàng trước khi đơn hàng 
được phê duyệt. Sau khi khách hàng xác nhận huỷ đơn hàng, toàn bộ số tiền sẽ được hoàn trả
vào phương thức thanh toán mà khách hàng đã sử dụng để thanh toán đơn hàng này thông qua 
VNPay.

Đơn hàng ở trạng thái chờ xử lý sẽ được người quản lý sản phẩm phê duyệt hoặc từ chối. Người 
quản lý sản phẩm có thể thấy 30 đơn hàng đang chờ xử lý trên mỗi trang. Từ đây, họ có thể chọn 
một đơn đặt hàng nào đó để xem chi tiết đơn đặt hàng và chấp thuận hoặc từ chối đơn hàng 
ngay cả khi có đủ sản phẩm trong kho. Có rất nhiều nguyên nhân như hàng trong tình trạng không 
nhận được, hết hàng trong khi khách đang thanh toán đơn hàng, hay đơn giản là trong kho không 
tìm được hàng. Tuy nhiên, việc chấp nhận đơn đặt hàng sẽ bị phần mềm từ chối nếu không có 
đủ sản phẩm trong kho.

Trong phạm vi môn học, các sinh viên kết nối với VNPay Sandbox. Một số thông tin và đặc tả về
cổng thanh toán có thể tham khảo:
  - Demo tại [VNPay Demo](https://sandbox.vnpayment.vn/apis/vnpay-demo/)
  - Đặc tả API thanh toán: [Documents Thanh toán](https://sandbox.vnpayment.vn/apis/docs/thanh-toan-pay/pay.html)
  - Đặc tả API truy vấn và hoàn tiền: [Documents Truy vấn và Hoàn tiền](https://sandbox.vnpayment.vn/apis/docs/truy-van-hoan-tien/querydr&refund.html)

const form = document.getElementById("loginForm");
const errorMsg = document.getElementById("errorMsg");

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
    const res = await fetch("api/users/login", {
        method: "POST",
        headers: {
        "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
    });

    if (!res.ok) {
        const err = await res.text();
        throw new Error(err);
    }

    const data = await res.json();
    localStorage.setItem("token", data.token); // JWT 저장
    alert("로그인 성공!");
    // TODO: 이후 페이지로 이동
    } catch (err) {
    errorMsg.textContent = "로그인 실패: " + err.message;
    errorMsg.classList.remove("d-none");
    }
});